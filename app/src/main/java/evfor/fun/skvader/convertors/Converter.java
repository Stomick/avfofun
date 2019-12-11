package evfor.fun.skvader.convertors;

public interface Converter<To ,From> {
    To convert(From from);
}
