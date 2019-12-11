package evfor.fun.skvader.dagger2.modules;

import evfor.fun.skvader.dagger2.scopes.MainScope;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.repository.LocalRepository;
import evfor.fun.skvader.repository.PaperRepos;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.repository.WriterRepos;
import evfor.fun.skvader.repository.act.ActReader;
import evfor.fun.skvader.repository.act.ActWriter;
import evfor.fun.skvader.repository.user.EditUserProfile;
import evfor.fun.skvader.repository.user.UserReader;

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
