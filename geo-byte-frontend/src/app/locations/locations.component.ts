import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AppService } from '../app.service';
import { AppConstants } from '../utils/constants';
import { LocationsService } from './locations.service';

@Component({
    selector: 'app-locations',
    templateUrl: './locations.component.html',
    styleUrls: ['./locations.component.css'],
    providers: [
        LocationsService
    ],
})
export class LocationsComponent implements OnInit {

    locations : any = [];

    selectedLocation : any = {};

    newLocation : any = {};

    updateForm = this.fb.group({
        name: ['', Validators.required],
        coordinate_x: ['', [Validators.required, Validators.min(0), Validators.max(40),  Validators.pattern('\\d+')]],
        coordinate_y: ['', [Validators.required, Validators.min(0), Validators.max(40),  Validators.pattern('\\d+')]],
        clearing_cost: ['', [Validators.required, Validators.min(25), Validators.max(100),  Validators.pattern('\\d+')]],
    });

    addForm = this.fb.group({
        name: ['', Validators.required],
        coordinate_x: ['', [Validators.required, Validators.min(0), Validators.max(40),  Validators.pattern('\\d+')]],
        coordinate_y: ['', [Validators.required, Validators.min(0), Validators.max(40),  Validators.pattern('\\d+')]],
        clearing_cost: ['', [Validators.required, Validators.min(25), Validators.max(100),  Validators.pattern('\\d+')]],
    });
    

    constructor(private app: AppService, private fb: FormBuilder, public locationsService: LocationsService, private router: Router) { }

    ngOnInit(): void {
        this.getAll();
        this.app.setActive('locations');
    }

    getAll(): void {
        this.app.toggleLoader(true);
        this.locationsService.getAll()
        .subscribe(
            (res : any) => {
                this.app.toggleLoader(false);
                if(res.success && res.status == AppConstants.OK) {
                    this.locations = res.data;
                }
            },
            (err : any) => {
                this.app.toggleLoader(false);
            }
        );
    }

    remove(location : any) {
        this.selectedLocation = location;
    }

    confirmRemoval() {
        this.app.toggleLoader(true);
        this.locationsService.remove(this.selectedLocation.id)
        .subscribe(
            (res : any) => {
                if(res.success && res.status == AppConstants.OK) {
                    this.app.notify("Location removed successfully");
                    this.getAll();
                }
            },
            (err : any) => {
                this.app.toggleLoader(false);
                this.app.error("Location could not be removed");
            }
        );
    }

    update(location : any) {
        this.selectedLocation = location;
        this.updateForm = this.fb.group({
            name: [location.name, Validators.required],
            coordinate_x: [location.coordinate_x, [Validators.required, Validators.min(0), Validators.max(40),  Validators.pattern('\\d+')]],
            coordinate_y: [location.coordinate_y, [Validators.required, Validators.min(0), Validators.max(40),  Validators.pattern('\\d+')]],
            clearing_cost: [location.clearing_cost, [Validators.required, Validators.min(25), Validators.max(100),  Validators.pattern('\\d+')]],
        });
    }

    onUpdate(): void {
        this.app.toggleLoader(true);
        this.locationsService.update(this.selectedLocation.id, this.updateForm.value)
        .subscribe(
            (res : any) => {
                if(res.success && res.status == AppConstants.OK) {
                    this.app.notify("Location updated successfully");
                    this.getAll();
                }
            },
            (err : any) => {
                this.app.toggleLoader(false);
                this.app.error("Location could not be updated");
            }
        );
    }

    onAdd(): void {
        this.app.toggleLoader(true);
        this.locationsService.add(this.addForm.value)
        .subscribe(
            (res : any) => {
                if(res.success && res.status == AppConstants.OK) {
                    this.app.notify("Location added successfully");
                    this.getAll();
                }
            },
            (err : any) => {
                this.app.toggleLoader(false);
                this.app.error("Location could not be added");
            }
        );
        this.addForm.reset();
    }

    getMapCoordAndAddLocation(e: any) {
        let rect = e.target.getBoundingClientRect();
        let xPos = e.clientX - rect.left; //x position within the element.
        let yPos = e.clientY - rect.top;  //y position within the element.
        
        let xCoord = Math.floor((xPos / e.target.clientWidth) * 40);
        let yCoord = Math.floor((yPos / e.target.clientHeight) * 40);
        
        this.newLocation.coordinate_x = xCoord;
        this.newLocation.coordinate_y = yCoord;
    }

    add(location : any) {
        this.addForm.setValue({
            'coordinate_x': location.coordinate_x ? location.coordinate_x : '', 
            'coordinate_y': location.coordinate_y ? location.coordinate_y : '', 
            'name': '', 
            'clearing_cost': ''
        });
        this.newLocation = {};
    }

}
