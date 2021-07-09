import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders  } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import {catchError} from 'rxjs/operators';
import { ErrorHandling } from './error.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient,private errorHandling:ErrorHandling) { }
  private baseURL=environment.baseUrl;

  searchStock(user):Observable<any> {
    return this.http.get<any>(this.baseURL+'search-service/api/v1.0/market/stock/get/'+user.companyCode+'/'+user.startDate+'/'+user.endDate).pipe(catchError(this.errorHandling.handleError))
    //(this.baseURL+'login/validateUser', user)
  }
getcompany(){
  
  return this.http.get<any>(this.baseURL+'search-service/api/v1.0/market/company/getall')
}
}
