import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';

import { routes } from './app.routes';
import { AppService } from './app.service';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { LocationsComponent } from './locations/locations.component';
import { RouteComponent } from './route/route.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { NotFoundComponent } from './not-found/not-found.component';

@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        SignInComponent,
        SignUpComponent,
        LocationsComponent,
        RouteComponent,
        NotFoundComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        RouterModule.forRoot(routes,
            { useHash: false }
        ),
        ReactiveFormsModule,
        HttpClientModule
    ],
    providers: [
        AppService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
