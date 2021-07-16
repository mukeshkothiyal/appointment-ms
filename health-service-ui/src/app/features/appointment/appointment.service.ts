import {Injectable} from "@angular/core";
import {IAppointment} from "./appointment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, of, throwError} from "rxjs";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private url!: string;

  constructor(private http: HttpClient) {
  }

  getUpcomingAppointments(): Observable<IAppointment[]> {
    this.url = 'https://localhost:9000/appointment/appointment/upcomingApp';
    return this.http.get<IAppointment[]>(this.url).pipe(
      tap(data => console.log(JSON.stringify(data))),
      catchError(this.handleError)
    );
  }

  postNewAppointments(appointment: IAppointment): Observable<IAppointment> {
    this.url = 'https://localhost:9000/appointment/appointment/';
    // TODO fix this!!!
    // return this.http.post<IAppointment>(this.url, appointment).pipe(
    //   tap(data => console.log(JSON.stringify(data))),
    //   catchError(this.handleError)
    // );
    return of(appointment);
  }

  private handleError(err: HttpErrorResponse) {
    console.log('Error handling logic here!!!')
    return throwError(err)
  }
}
