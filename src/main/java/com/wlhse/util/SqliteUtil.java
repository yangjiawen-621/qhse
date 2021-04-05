package com.wlhse.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URLDecoder;
import java.sql.*;
import java.util.Properties;

@Component
public class SqliteUtil {

    private String enter = "\r\n";
    private String splitLine = "/* sql语句分界--B511制作b4c8c28d70421a60cbb83523294f0e33-- */"+enter;
    //获取db.properties里的配置属性
    public String getProperty(String proName) throws IOException {
        String path = SqliteUtil.class.getClassLoader().getResource("db.properties").getPath();
        path = URLDecoder.decode(path,"GBK");
        FileInputStream in = new FileInputStream(path);
        Properties prop = new Properties();
        prop.load(in);
        return prop.getProperty(proName);
    }

    //判断字符串是否为空
    public boolean isEmpty(String str) {
        // 如果字符串不为null，去除空格后值不与空字符串相等的话，证明字符串有实质性的内容
        if (str != null && !str.trim().isEmpty()) {
            return false;// 不为空
        }
        return true;// 为空
    }

    //将字符串转化为utf-8编码
    public String toUTF8(String str) {
        if (isEmpty(str)) {
            return "";
        }
        try {
            if (str.equals(new String(str.getBytes("GB2312"), "GB2312"))) {
                str = new String(str.getBytes("GB2312"), "utf-8");
                return str;
            }
        } catch (Exception exception) {
        }
        try {
            if (str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
                str = new String(str.getBytes("ISO-8859-1"), "utf-8");
                return str;
            }
        } catch (Exception exception1) {
        }
        try {
            if (str.equals(new String(str.getBytes("GBK"), "GBK"))) {
                str = new String(str.getBytes("GBK"), "utf-8");
                return str;
            }
        } catch (Exception exception3) {
        }
        return str;
    }

    //文件编码是否为utf-8
    public boolean isEncodeUtf8(String path) throws IOException {
        File file = new File(path);
        InputStream in= new FileInputStream(file);
        byte[] b = new byte[3];
        in.read(b);
        in.close();
        if (b[0] == -17 && b[1] == -69 && b[2] == -65)
            return true;
        else
            return false;
    }

    //读取sql文件，将其处理成sqlite可以执行的文件
    public String readSqlFile(String rFilePath) {
        int FLAG = 0;
        File file = new File(rFilePath);
        BufferedReader reader = null;
        StringBuffer buf = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                if (tempString.contains("AUTO_INCREMENT")) {
                    tempString = tempString.replace(" AUTO_INCREMENT", "");
                }
                if (tempString.contains("ENGINE")) {
                    tempString = ");";
                }
                if (tempString.contains("PRIMARY KEY") && tempString.contains("USING")) {
                    tempString = tempString.replace("USING BTREE", "");
                }
                if (tempString.startsWith("CREATE")) {
                    FLAG = 1;
                }
                if (tempString.startsWith("INSERT")) {
                    FLAG = 1;
                }
                if (tempString.startsWith("/* sql语句分界")) {
                    FLAG = 1;
                }
                if (tempString.endsWith(");")) {
                    buf.append(tempString + enter);
                    FLAG = 0;
                }
                if (FLAG != 0 || tempString.startsWith("DROP")) {
                    buf.append(tempString + enter);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(reader!=null) {
                try {
                    reader.close();
                } catch (IOException e1) {

                }
            }
            }
        return buf.toString();
    }

    //将处理好的内容写回wFilePath所在文件里
    private int writeFile(String updatedContent, String wFilePath){
        BufferedWriter bw=null;
        try{
            bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(wFilePath),"utf-8"));
            bw.write(updatedContent);
        }catch(Exception e){
            e.printStackTrace();
        }finally{if(bw!=null){
            try{
                bw.close();
            }catch(IOException e){
                bw=null;
            }
        }
        }
        return 1;
    }

    //释放连接
    public static void free(Statement st, Connection conn) {
        try {
            if (st != null)
                st.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {

        }
    }

    public String getCreateString(String tableName,Connection conn,String autoincrement) throws SQLException {
        String primaryKeyColumnName = "";
        StringBuffer createSqlString = new StringBuffer(splitLine+"DROP TABLE IF EXISTS `"+tableName+"`;"+enter);
        createSqlString.append(splitLine).append("CREATE TABLE `").append(tableName).append("`(").append(enter);
        DatabaseMetaData dbmd;
        dbmd = conn.getMetaData();
        ResultSet primaryKeyResultSet = null;
        String autoincrementString = "";
        try {
            primaryKeyResultSet = dbmd.getPrimaryKeys(null,null,tableName);
            boolean hasPrimaryKey = primaryKeyResultSet.next();
            if(hasPrimaryKey) {
                primaryKeyColumnName = primaryKeyResultSet.getString("COLUMN_NAME");
            }
            ResultSet columns = dbmd.getColumns(null,null,tableName,null);
            while(columns.next()){
                String colName = columns.getString("COLUMN_NAME");
                String colSize = columns.getString("COLUMN_SIZE");
                String colType = columns.getString("TYPE_NAME");
                String isAutoIncrement = columns.getString("IS_AUTOINCREMENT");
                colType = colType.toLowerCase();
                if(colName.equals(primaryKeyColumnName)){
                    if(colType.equals("date")||colType.equals("datetime")||colType.equals("double")){
                        createSqlString.append("`"+colName+"` "+colType+" NOT NULL");
                    }
                    else{
                        createSqlString.append("`"+colName+"` "+colType+"("+colSize+") NOT NULL");
                    }
                }
                else {
                    if(colType.equals("date")||colType.equals("datetime")||colType.equals("double")){
                        createSqlString.append("`"+colName+"` "+colType+" DEFAULT NULL");
                    }
                    else{
                        createSqlString.append("`"+colName+"` "+colType+"("+colSize+") DEFAULT NULL");
                    }
                }
                if(isAutoIncrement.equals("YES")){
                    createSqlString.append(" AUTO_INCREMENT,"+enter);
                    autoincrementString = autoincrementString+"AUTO_INCREMENT="+autoincrement;
                }
                else{
                    createSqlString.append(","+enter);
                }
            }
            //结束遍历，写create语句尾部
            if(hasPrimaryKey) {
                createSqlString.append("PRIMARY KEY (`" + primaryKeyColumnName + "`)" + enter + ")ENGINE=InnoDB "+autoincrementString+" DEFAULT CHARSET=utf8;" + enter);
            }
            else{
                createSqlString.deleteCharAt(createSqlString.length()-3).append(")ENGINE=InnoDB "+autoincrementString+" DEFAULT CHARSET=utf8;" + enter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
        return createSqlString.toString();
    }

    private String getDataString(String tableName, ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        StringBuilder tableString = new StringBuilder(splitLine+"INSERT INTO `"+tableName+"` VALUES (");
        boolean isEmpty = true;
        int id = 0;
        while(rs.next()){
            for(int i=1;i<=columnCount;i++) {
                Object object = rs.getObject(i);
                if(object==null){
                    object=object;
                }else {
                    String typeName = object.getClass().getTypeName();
                    if (typeName.equals("java.lang.String")||typeName.equals("java.sql.Date")||typeName.equals("java.sql.datetime")) {
                        object="'"+object+"'";
                    }
                }
                if(i==columnCount){
                    tableString.append(object+"),(");
                }
                else{
                    tableString.append(object+",");
                }
                if(i==1){
                    id = (int)object;
                }
            }
            isEmpty = false;
        }
        tableString.delete(tableString.length()-2,tableString.length()).append(";"+enter);
        id = 1+id;
        if(isEmpty){
            return " "+"woshifengefu"+id;
        }
        return tableString.toString()+"woshifengefu"+id;
    }

    private String exportSqlString(String tableName) throws SQLException, IOException, ClassNotFoundException {
        String tablesql;
        String datasql;
        Connection conn = null;
        Statement stat = null;
        String url = getProperty("jdbc.url");
        String username = getProperty("jdbc.user");
        String password = getProperty("jdbc.password");
        String driver = getProperty("jdbc.driver");
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);//得到数据库连接
            stat = conn.createStatement();//创建Statement对象
            ResultSet rs = stat.executeQuery("select * from " + tableName);//执行查询语句
            datasql = getDataString(tableName,rs);
            String autoincrement = datasql.split("woshifengefu")[1];
            datasql = datasql.split("woshifengefu")[0];
            tablesql = getCreateString(tableName,conn,autoincrement);

        } finally {
            free(stat, conn);
        }
        return tablesql+datasql;
    }

    //集成导出、读、写函数，实现功能
    public String gather(String folderPath,String fileName) throws IOException, ClassNotFoundException {
        String tables = "checkitem,checkitemstardard," +
                "employee,problemdescription,problemfactor," +
                "problemsource,sys_company,taskandprocess,datadict";
        String[] table = tables.split(",");
        String mysqlPath = folderPath + "\\mysql" + fileName + ".sql";
        String sqlitePath = folderPath + "\\sqlite" + fileName + ".sql";

        StringBuilder rSql = new StringBuilder();
        //读取sql文件并将它处理成sqlite可处理的字符串
        for (String i : table) {
            String newSql = null;
            try {
                newSql = exportSqlString(i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rSql.append(newSql);
        }

        //转化编码格式
        rSql = new StringBuilder(toUTF8(rSql.toString()));
        //写回mysql
        int isWriteSuccess = writeFile(rSql.toString(), mysqlPath);
        //判断是否写回成功
        if(isWriteSuccess==1){
            //读mysql
            rSql = new StringBuilder(readSqlFile(mysqlPath));

            //写回sqlite
            writeFile(rSql.toString(),sqlitePath);

            return  fileName + ".sql";
        }
        return  fileName + ".sql";
    }

}
