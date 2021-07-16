import {Component, OnInit} from '@angular/core';
import {IAppointment} from "../appointment";
import {AppointmentService} from "../appointment.service";
import {NgForm} from "@angular/forms";

@Component({
  templateUrl: './create-appointment.component.html',
  styleUrls: ['./create-appointment.component.css']
})
export class CreateAppointmentComponent implements OnInit {

  appointment!: IAppointment;
  submitted: boolean = false;
  private errorMessage: string = '';

  constructor(private appointmentService: AppointmentService) {
  }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    this.setAppointment(form.value);
    console.log("Appointment object ready to be sent out:: " + JSON.stringify(this.appointment))
    this.submitted = true;
    this.appointmentService.postNewAppointments(this.appointment).subscribe(
      {
        next: result => {
          this.appointment = result
          console.log("submitted request: " + this.appointment);
        },
        error: err => {
          this.errorMessage = err
          console.log(this.errorMessage)
        }
      }
    );
  }

  private setAppointment(app: any) {
    // TODO appointment object is not setting up  correctly
    this.appointment = {
      appointmentId: '',
      patientId: '',
      doctorId: '',
      operatorId: '',
      doctorName: app.doctorName,
      patientName: app.patientName,
      operatorName: '',
      additionalComment: app.symptoms,
      appointmentTime: app.slot
    }
  }
}
