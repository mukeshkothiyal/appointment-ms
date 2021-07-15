import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserComponent} from "./user/user.component";
import {AppointmentComponent} from "./appointment/appointment.component";

const routes: Routes = [
  {path: 'user/:userId', component: UserComponent},
  {path: 'appointments', component: AppointmentComponent},
  {path: '', component: AppointmentComponent, pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
