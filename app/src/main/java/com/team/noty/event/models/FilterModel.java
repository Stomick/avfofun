package com.team.noty.event.models;

import com.team.noty.event.utils.ObjUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FilterModel implements Serializable {
    public String word;
    public String city;
    public String date;
    public String datecreate;
    public String time;
    public String cat;
    public Integer age;
    public Integer max_parts;
    public Integer price_up_to;
    public Sort sort;
    public boolean only_event;
    public boolean only_comm;
    public Popular pop;

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        input(map, "word", word);
        input(map, "city", city);
        input(map, "date", date);
        input(map, "datecreate", datecreate);
        input(map, "time", time);
        input(map, "cat", cat);
        input(map, "age", age);
        input(map, "count_p", max_parts);
        input(map, "price", price_up_to);
        if (pop==null)
            pop = Popular.dates;
        input(map, "pop", pop);
        if(only_event)
            input(map, "comm", "on");
        if(only_comm)
            input(map, "event","on");
        return map;
    }

    private void input(Map<String, String> map, String key, Object value) {
        if (value != null && !String.valueOf(value).isEmpty())
            map.put(key, String.valueOf(value));
    }

    public boolean isOnly_event() {
        return only_event;
    }

    public void setOnly_event(boolean only_event) {
        this.only_event = only_event;
    }

    public void setOnly_comm(boolean only_comm) {
        this.only_comm = only_comm;
    }

    public boolean isOnly_comm() {
        return only_comm;
    }

    public FilterModel setCat(String cat) {
        this.cat = cat;
        return this;
    }

    public FilterModel setWord(String word) {
        this.word = word;
        return this;
    }

    public FilterModel setDatecreate(String datecreate) {
        this.datecreate = datecreate;
        return this;
    }

    public FilterModel setPop(Popular pop) {
        this.pop = pop;
        return this;
    }

    public FilterModel setCity(String city) {
        this.city = city;
        return this;
    }

    public FilterModel setDate(String date) {
        this.date = date;
        return this;
    }

    public FilterModel setTime(String time) {
        this.time = time;
        return this;
    }

    public FilterModel setAge(int age) {
        this.age = age;
        return this;
    }

    public FilterModel setMax_parts(int max_parts) {
        this.max_parts = max_parts;
        return this;
    }

    public FilterModel setPrice_up_to(int price_up_to) {
        this.price_up_to = price_up_to;
        return this;
    }

    public FilterModel setSort(Sort sort) {
        this.sort = sort;
        return this;
    }

    public enum Sort {
        NAME(2), DATE(1);
        private int num;

        Sort(int n) {
            num = n;
        }

        public int getNum() {
            return num;
        }
    }

    public enum Popular {
        up, down, dates
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FilterModel)
            return ObjUtils.equals(word, ((FilterModel) obj).word)
                    && ObjUtils.equals(city, ((FilterModel) obj).city)
                    && ObjUtils.equals(date, ((FilterModel) obj).date)
                    && ObjUtils.equals(datecreate, ((FilterModel) obj).datecreate)
                    && ObjUtils.equals(time, ((FilterModel) obj).time)
                    && ObjUtils.equals(cat, ((FilterModel) obj).cat)
                    && ObjUtils.equals(age, ((FilterModel) obj).age)
                    && ObjUtils.equals(max_parts, ((FilterModel) obj).max_parts)
                    && ObjUtils.equals(price_up_to, ((FilterModel) obj).price_up_to)
                    && ObjUtils.equals(sort, ((FilterModel) obj).sort);
        return super.equals(obj);
    }
}
