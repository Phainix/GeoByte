import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppBaseService } from '../app.base.service';

@Injectable()
export class LocationsService extends AppBaseService  {
    constructor(private http: HttpClient) {
        super();
    }

    getAll() {
        return this.http.get(`${this.baseURL}/location/getAll`).pipe(
        );
    }

    add(data : any) {
        return this.http.post(`${this.baseURL}/location/add`, data).pipe(
        );
    }

    remove(location_id : any) {
        return this.http.get(`${this.baseURL}/location/remove?location_id=${location_id}`).pipe(
        );
    }

    update(location_id : any, data : any) {
        return this.http.post(`${this.baseURL}/location/update?location_id=${location_id}`, data).pipe(
        );
    }

}