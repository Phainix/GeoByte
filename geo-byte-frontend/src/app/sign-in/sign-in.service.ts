import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppBaseService } from '../app.base.service';

@Injectable()
export class SignInService extends AppBaseService  {
    constructor(private http: HttpClient) {
        super();
    }

    signIn(data : any) {
        return this.http.post(`${this.baseURL}/user/signIn`, data).pipe(
            // catchError(this.handleError)
        );
    }

}