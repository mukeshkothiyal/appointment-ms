import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PsvPipe} from "./psv.pipe";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    PsvPipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    PsvPipe
  ]
})
export class SharedModule {
}
