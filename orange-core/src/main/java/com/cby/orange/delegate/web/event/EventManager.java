package com.cby.orange.delegate.web.event;

import java.util.HashMap;

import io.reactivex.annotations.Nullable;

/**
 * Created by baiyanfang on 2017/12/29.
 */

public class EventManager {

    private static final HashMap<String,Event> EVENTS = new HashMap<>();

    private EventManager() {
    }

    private static class Holder{
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance(){
        return Holder.INSTANCE;
    }

    public EventManager addEvent(@Nullable String name, @Nullable Event event){
        EVENTS.put(name,event);
        return this;
    }

    public Event createEvent(@Nullable String action){
        Event event = EVENTS.get(action);
        if (event == null){
            event = new UndefineEvent();
        }
        return event;
    }
}
