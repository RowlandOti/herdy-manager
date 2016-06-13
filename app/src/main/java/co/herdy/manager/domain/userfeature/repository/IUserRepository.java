package co.herdy.manager.domain.userfeature.repository;

import co.herdy.manager.data.userfeature.payload.UserPayload;
import co.herdy.manager.domain.repository.IRepository;
import rx.Observable;

/*
* Force any User specific logic for implementation here
* */
public interface IUserRepository extends IRepository {

    Observable<String> authLoginUser(String email, String password);

    Observable<UserPayload> authRegisterUser(UserPayload userPayload);
}
