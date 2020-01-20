import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { AgGridModule } from 'ag-grid-angular';

// import '../../node_modules/ag-grid-community/src/styles/ag-grid.scss';
// import '../../node_modules/ag-grid-community/src/styles/ag-theme-balham/sass/ag-theme-balham.scss';

// import '../../node_modules/ag-grid-community/dist/styles/ag-theme-balham.css';

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, AppRoutingModule, AgGridModule.withComponents([])],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
