import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

import { AppService } from '../app.service';
import { AppConstants } from '../utils/constants';
import { SignUpService } from './sign-up.service';

@Component({
    selector: 'app-sign-up',
    templateUrl: './sign-up.component.html',
    styleUrls: ['./sign-up.component.css'],
    providers: [
        SignUpService
    ],
})
export class SignUpComponent implements OnInit {

    signUpForm = this.fb.group({
        name: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(5)]],
    });

    user = {};

    constructor(private app: AppService, private fb: FormBuilder, public signUpService: SignUpService, private router: Router) { }

    ngOnInit(): void {
    }

    onSubmit() {
        this.app.toggleLoader(true);
        this.signUpService.signUp(this.signUpForm.value)
        .subscribe(
            (res : any) => {
                this.app.toggleLoader(false);
                if(res.success && res.status == AppConstants.OK) {
                    this.app.notify("Your account has been successfully created, please login");
                    this.router.navigateByUrl('/sign-in');
                    return;
                }
                this.app.error("User account could not be created, please try again");
            },
            err => {
                this.app.toggleLoader(false);
                if(err.error && err.error.status == AppConstants.DUPLICATE_ENTITY) {
                    this.app.error("User account exists, please login");
                }
            }
        );
    }

}
