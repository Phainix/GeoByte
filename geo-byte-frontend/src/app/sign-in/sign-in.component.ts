import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AppService } from '../app.service';
import { AppConstants } from '../utils/constants';
import { SignInService } from './sign-in.service';

@Component({
    selector: 'app-sign-in',
    templateUrl: './sign-in.component.html',
    styleUrls: ['./sign-in.component.css'],
    providers: [
        SignInService
    ],
})
export class SignInComponent implements OnInit {

    signInForm = this.fb.group({
        email: ['', [Validators.required, Validators.email]],
        password: ['', Validators.required],
    });

    constructor(private app: AppService, private fb: FormBuilder, public signInService: SignInService, private router: Router) { }

    ngOnInit(): void {
    }

    onSubmit() {
        this.app.toggleLoader(true);
        this.signInService.signIn(this.signInForm.value)
        .subscribe(
            (res : any) => {
                this.app.toggleLoader(false);
                if(res.success && res.status == AppConstants.OK) {
                    localStorage.setItem("user", res.data.name);
                    localStorage.setItem("userId", res.data.id);
                    
                    this.app.notify("Login successful");
                    this.router.navigateByUrl('/locations');
                    return;
                }
                this.app.error("Email or Password incorrect, please try again");
            },
            (err : any) => {
                this.app.toggleLoader(false);
                this.app.error("Email or Password incorrect, please try again");
            }
        );
    }

}
