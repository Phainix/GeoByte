import { of } from 'rxjs';

export class AppBaseService {

    baseURL: string = 'http://localhost:8080/v1';
  
    public handleError(error : any) {
        if (error.error instanceof ErrorEvent) {
            console.log(`Error: ${error.error.message}`);
        } else {
            console.log(`Error: ${error.message}`);
        }
        return of([]);
    }
}