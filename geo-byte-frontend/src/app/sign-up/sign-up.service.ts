import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppBaseService } from '../app.base.service';

@Injectable()
export class SignUpService extends AppBaseService  {
    constructor(private http: HttpClient) {
        super();
    }

    signUp(data : any) {
        return this.http.post(`${this.baseURL}/user/signUp`, data).pipe(
            // catchError(this.handleError)
        );
    }

}