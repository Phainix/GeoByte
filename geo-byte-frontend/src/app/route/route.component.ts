import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AppService } from '../app.service';
import { AppConstants } from '../utils/constants';
import { RouteService } from './route.service';

@Component({
    selector: 'app-route',
    templateUrl: './route.component.html',
    styleUrls: ['./route.component.css'],
    providers: [
        RouteService
    ],
})
export class RouteComponent implements OnInit {

    locations : any = [];

    optimalRoute : any = {};

    worstRoute : any = {};

    routeForm = this.fb.group({
        origin: ['', Validators.required],
        destination: ['', Validators.required],
    });

    routeLoaded = false;


    constructor(private app: AppService, private fb: FormBuilder, public routeService: RouteService, private router: Router) { }

    ngOnInit(): void {
        this.getAll();
        this.app.setActive('route');
    }

    getAll(): void {
        this.app.toggleLoader(true);
        this.routeService.getAll()
        .subscribe(
            (res : any) => {
                this.app.toggleLoader(false);
                if(res.success && res.status == AppConstants.OK) {
                    this.locations = res.data;
                    if(this.locations.length < 3) {
                        this.app.error("You need to add at least 3 locations before generating a route, please add more locations");
                    }
                }
            },
            (err : any) => {
                this.app.toggleLoader(false);
            }
        );
    }

    onGenerateRoute() {
        if(this.locations.length < 3) {
            this.app.error("You need to add at least 3 locations before generating a route, please add more locations");
        }
        if(this.routeForm.value.origin == this.routeForm.value.destination) {
            this.app.error("Your origin location cannot be the same as the destination location, please try again");
        }

        this.routeService.get(this.routeForm.value.origin, this.routeForm.value.destination)
        .subscribe(
            (res : any) => {
                this.app.toggleLoader(false);
                if(res.success && res.status == AppConstants.OK) {
                    console.log(res);
                    if(res.data.length < 1) {
                        this.app.error("No routes available for selected origin and destination");
                        return;
                    }
                    this.optimalRoute = res.data[0];
                    this.worstRoute = res.data.length > 1 ? res.data[res.data.length - 1] : false;
                    this.routeLoaded = true;
                }
            },
            (err : any) => {
                this.app.toggleLoader(false);
                if(err.error && err.error.status == AppConstants.NOT_FOUND) {
                    this.app.error("Selected location could not be retrieved, please login");
                }
            }
        );
    }

    getLocationName(id : any) {
        for (let index = 0; index < this.locations.length; index++) {
            let location : any = this.locations[index];
            if(location.id == id) return location.name;
        }
    }

}
