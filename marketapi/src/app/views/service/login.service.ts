import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  constructor(private http: HttpClient) { }
  private baseURL=environment.baseUrl;

  loginUser(user) {
    return this.http.post<any>(this.baseURL+'login-service/api/v1.0/market/login',user)
    //(this.baseURL+'login/validateUser', user)
  }

  getToken() {
    console.log("token ::::::::: "+localStorage.getItem('token'));
    return localStorage.getItem('token')
  }

  loggedIn() {
    return !!localStorage.getItem('token')    
  }
}
