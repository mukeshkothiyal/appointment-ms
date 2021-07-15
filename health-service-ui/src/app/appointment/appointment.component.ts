import {Component, OnDestroy, OnInit} from '@angular/core';
import {IAppointment} from "./appointment";
import {AppointmentService} from "./appointment.service";
import {Subscriber, Subscription} from "rxjs";

@Component({
  selector: 'hs-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent implements OnInit, OnDestroy {
  mockAppointments: IAppointment[] = [];
  filteredMockAppointments: IAppointment[] = [];
  errorMessage: string = '';
  sub!: Subscription;

  constructor(private appointmentService: AppointmentService) {
  }

  ngOnInit(): void {
    console.log('Appointment component initialized!!!')
    this.sub = this.appointmentService.getUpcomingAppointments().subscribe({
      next: upcomingAppointments => {
        this.mockAppointments = upcomingAppointments;
        this.filteredMockAppointments = this.mockAppointments;
      },
      error: err => this.errorMessage = err
    });
  }

  private _filterName: string = '';

  get filterName(): string {
    return this._filterName;
  }

  set filterName(value: string) {
    this._filterName = value;
    this.filteredMockAppointments = this.performFilter(value);
  }

  private performFilter(filterBy: string): IAppointment[] {
    filterBy = filterBy.toLowerCase();
    return this.mockAppointments.filter((app: IAppointment) => app.patientId.toLowerCase().includes(filterBy));
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
