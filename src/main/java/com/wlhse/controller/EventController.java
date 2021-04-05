package com.wlhse.controller;


import com.wlhse.dto.QHSEEventDto;
import com.wlhse.service.EventService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("EventController")
@RequestMapping("/api/v3")
public class EventController {
    @Resource
    private EventService eventService;

    @RequestMapping(value = "/add_event", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public R addEvent(@RequestBody(required = false) QHSEEventDto qhseEventDto)
    {
        return eventService.addEvent(qhseEventDto);
    }

    @RequestMapping(value = "/delete_event/{id}", method = RequestMethod.DELETE,produces = {"application/json;charset=UTF-8"})
    public R deleteEvent(@PathVariable int id)
    {
        return eventService.deleteEvent(id);
    }

    @RequestMapping(value = "/update_event/{id}", method = RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
    public R updateEvent(@PathVariable int id, @RequestBody(required = false) QHSEEventDto qhseeventDto){
        qhseeventDto.setEventID(id);
        return eventService.updateEvent(qhseeventDto);
    }

    @RequestMapping(value = "/query_event", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String queryEvent(@ModelAttribute QHSEEventDto qhseeventDto) {
        return eventService.queryEvent(qhseeventDto);
    }

    @RequestMapping(value = "/query_event/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryEventById(@PathVariable int id) {
        return eventService.queryEventById(id);
    }
}
