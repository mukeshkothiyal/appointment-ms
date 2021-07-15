import {NgModule} from '@angular/core';
import {AppointmentComponent} from "./appointment/appointment.component";
import {UserComponent} from "./user/user.component";
import {RouterModule, Routes} from "@angular/router";
import {SharedModule} from "../shared/shared.module";

const routes: Routes = [
  {path: 'user/:userId', component: UserComponent},
  {path: 'appointments', component: AppointmentComponent}
];

@NgModule({
  declarations: [
    AppointmentComponent,
    UserComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ]
})
export class FeatureModule {
}
