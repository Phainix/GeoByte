import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AppBaseService } from './app.base.service';

@Injectable()
export class AppService extends AppBaseService {

    authenticated = false;
    alert = {};
    loading = false;

    constructor(private http: HttpClient) {
        super();
    }

    authenticate(credentials : any, callback : any) {

        const headers = new HttpHeaders(credentials ? {
            authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        } : {});

        this.http.get(`${this.baseURL}/user`, { headers: headers })
        .pipe(
            catchError(error => this.handleError(error))
        )
        .subscribe((response : any) => {
            if (response['success']) {
                this.authenticated = true;
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        });

    }

    notify(message : string) {
        this.alert = {
            message: message,
            type: "success"
        }
        this.clearAlert();
    }

    error(message : string) {
        this.alert = {
            message: message,
            type: "danger"
        }
        this.clearAlert();
    }

    clearAlert() {
        setTimeout(() => {
            this.alert = {};
        }, 10000);
    }

    toggleLoader(status : boolean) {
        this.loading = status
    }

}