import {Injectable} from "@angular/core";
import {IAppointment} from "./appointment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private upcomingAppointments = 'https://localhost:9000/appointment/appointment/upcomingApp';

  constructor(private http: HttpClient) {
  }

  getUpcomingAppointments(): Observable<IAppointment[]> {
    return this.http.get<IAppointment[]>(this.upcomingAppointments).pipe(
      tap(data => console.log(JSON.stringify(data))),
      catchError(this.handleError)
    );
  }

  private handleError(err: HttpErrorResponse) {
    console.log('Error handling logic here!!!')
    return throwError(err)
  }
}
