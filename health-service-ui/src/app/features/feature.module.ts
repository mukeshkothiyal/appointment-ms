import {NgModule} from '@angular/core';
import {UpcomingAppointmentComponent} from "./appointment/upcoming-appointment/upcomingAppointment.component";
import {UserDetailComponent} from "./user/user-detail/userDetail.component";
import {RouterModule, Routes} from "@angular/router";
import {SharedModule} from "../shared/shared.module";
import { CreateAppointmentComponent } from './appointment/create-appointment/create-appointment.component';

const routes: Routes = [
  {path: 'user/:userId', component: UserDetailComponent},
  {path: 'appointments', component: UpcomingAppointmentComponent}
];

@NgModule({
  declarations: [
    UpcomingAppointmentComponent,
    UserDetailComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ]
})
export class FeatureModule {
}
