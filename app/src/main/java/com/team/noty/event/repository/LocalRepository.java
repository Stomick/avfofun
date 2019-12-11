package com.team.noty.event.repository;

public interface LocalRepository<Out extends Identified, In> extends ReaderRepos<Out, In>, WriterRepos<Out> {
}
