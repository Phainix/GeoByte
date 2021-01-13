import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LocationsComponent } from './locations/locations.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { RouteComponent } from './route/route.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';

export const routes: Routes = [
	{
	    path: '',
	    component: HomeComponent
	},
	{
	    path: 'sign-in',
	    component: SignInComponent
	},
	{
	    path: 'sign-up',
	    component: SignUpComponent
	},
    {
	    path: 'locations',
	    component: LocationsComponent
	},
    {
	    path: 'route',
	    component: RouteComponent
	},
    { 
        path: '**', 
        component: NotFoundComponent 
    }
];