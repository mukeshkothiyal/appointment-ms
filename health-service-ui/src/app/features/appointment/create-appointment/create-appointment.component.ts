import {Component, OnInit} from '@angular/core';
import {IAppointment} from "../appointment";
import {AppointmentService} from "../appointment.service";
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  templateUrl: './create-appointment.component.html',
  styleUrls: ['./create-appointment.component.css']
})
export class CreateAppointmentComponent implements OnInit {

  appointment!: IAppointment;
  submitted: boolean = false;
  errorMessage: string = '';
  isError: boolean = false;

  constructor(private appointmentService: AppointmentService, private router: Router) {
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
          console.log("submitted request: " + JSON.stringify(this.appointment));
          this.router.navigate(["/appointments"])
        },
        error: err => {
          this.isError = true;
          this.errorMessage = "Error while submitting request: " + err.statusText;
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
