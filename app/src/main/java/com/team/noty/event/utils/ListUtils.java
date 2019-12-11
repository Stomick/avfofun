package com.team.noty.event.utils;

import com.team.noty.event.exceptions.NotFoundException;
import com.team.noty.event.repository.Identified;

import java.util.List;

public class ListUtils {
    public static String toStringWOBrackets(List list) {
        return String.valueOf(list).replace("[", "").replace("]", "");
    }

    public static <T extends Identified> T search(List<T> list, String id) throws NotFoundException {
        for (T t : list)
            if (t.id().equalsIgnoreCase(id))
                return t;
        throw new NotFoundException(id);
    }
}
