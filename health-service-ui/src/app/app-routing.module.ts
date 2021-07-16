import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UpcomingAppointmentComponent} from "./features/appointment/upcoming-appointment/upcomingAppointment.component";

const routes: Routes = [
  {path: '', component: UpcomingAppointmentComponent, pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
