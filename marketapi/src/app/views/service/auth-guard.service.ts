import { Injectable, Injector } from '@angular/core';
import { Router, CanActivate,ActivatedRouteSnapshot,RouterStateSnapshot } from '@angular/router'
import { LoginService } from '../service/login.service';
@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private router: Router,
    private authenticationService: LoginService){}

  canActivate(route:ActivatedRouteSnapshot,state:RouterStateSnapshot){
    const currentUser=this.authenticationService.loggedIn();
    if(currentUser){
      return true;
    }
    this.router.navigate(['/']);
    return false;
  }

}