package com.team.noty.event.convertors;

public interface Converter<To ,From> {
    To convert(From from);
}
