package com.team.noty.event.dagger2.modules;

import com.team.noty.event.dagger2.scopes.MainScope;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.User;
import com.team.noty.event.repository.LocalRepository;
import com.team.noty.event.repository.PaperRepos;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.repository.WriterRepos;
import com.team.noty.event.repository.act.ActReader;
import com.team.noty.event.repository.act.ActWriter;
import com.team.noty.event.repository.user.EditUserProfile;
import com.team.noty.event.repository.user.UserReader;

import dagger.Binds;
import dagger.Module;

@Module
public interface RepositoryModule {

    @MainScope
    @Binds
    ReaderRepos<User, Integer> provideUserReaderRepos(UserReader userReader);

    @MainScope
    @Binds
    WriterRepos<User> provideUserWriterRepos(EditUserProfile editUserProfile);

    @MainScope
    @Binds
    WriterRepos<Act> provideActWriterRepos(ActWriter actWriter);

    @MainScope
    @Binds
    ReaderRepos<Act, ActId> provideActReaderRepos(ActReader actReader);

    @MainScope
    @Binds
    LocalRepository<User, Integer> provideLocalUserRepos(PaperRepos<User> paperRepos);
}
