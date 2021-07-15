import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppointmentComponent } from './appointment/appointment.component';
import {FormsModule} from "@angular/forms";
import {PsvPipe} from "./shared/psv.pipe";
import {HttpClientModule} from "@angular/common/http";
import { UserComponent } from './user/user.component';

@NgModule({
  declarations: [
    AppComponent,
    AppointmentComponent,
    PsvPipe,
    UserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
