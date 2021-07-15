import {Injectable} from "@angular/core";
import {IUser} from "./user";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  getUserDetail(userId: string | null): Observable<IUser> {
    let userDetail = 'https://localhost:9000/appointment/user/' + userId;
    return this.http.get<IUser>(userDetail).pipe(
      tap(data => console.log(JSON.stringify(data))),
      catchError(this.handleError)
    );
  }

  private handleError(err: HttpErrorResponse) {
    console.log('Error handling logic here!!!')
    return throwError(err)
  }

}
