import { throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

export class ErrorHandling  {

  public handleError(error:HttpErrorResponse){
    if(error.error instanceof ErrorEvent){
      console.log('An error occurred : '+error.error.message);
    }else{
      console.log('Backend return code '+error.status)

      console.log('An error occurred : '+error.error.message);
      alert("service Erroe :::::::: "+error.error.message);
    }return throwError('something bad happened; please try again later');
  };
  }

