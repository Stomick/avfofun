package evfor.fun.skvader.repository;

public interface LocalRepository<Out extends Identified, In> extends ReaderRepos<Out, In>, WriterRepos<Out> {
}
