import { Component } from '@angular/core';
import { AppService } from './app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { catchError } from 'rxjs/operators';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'geo-byte-frontend';

    constructor(private app: AppService, private http: HttpClient, private router: Router) {
        this.app.authenticate(undefined, undefined);
    }

    getAlert() : any {
        return this.app.alert;
    }

    getLoading() : any {
        return this.app.loading;
    }

    logout() {
        this.http.post('logout', {})
        .pipe(
            catchError(error => this.app.handleError(error))
        )
        .subscribe(() => {
            this.app.authenticated = false;
            this.router.navigateByUrl('/sign-in');
        });
    }
}
