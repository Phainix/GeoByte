import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppBaseService } from '../app.base.service';

@Injectable()
export class RouteService extends AppBaseService  {
    constructor(private http: HttpClient) {
        super();
    }

    get(origin_location_id : any, destination_location_id : any) {
        return this.http.get(`${this.baseURL}/location/optimal/route?origin_location_id=${origin_location_id}&destination_location_id=${destination_location_id}`).pipe(
        );
    }

    getAll() {
        return this.http.get(`${this.baseURL}/location/getAll`).pipe(
        );
    }
}