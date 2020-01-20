import { Component, ViewChild, OnInit } from '@angular/core';
// import { AgGridAngular } from 'ag-grid-angular';
import { AllCommunityModules } from '@ag-grid-community/all-modules';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss', './ag-grid.scss'],
  // styleUrls: ['../../node_modules/ag-grid-community/src/styles/ag-grid.scss', './app.component.scss'],
})
export class AppComponent {
  // @ViewChild('agGrid') agGrid: AgGridAngular;

  title = 'myAgGrid';

  // public modules: Module[] = AllModules;

  columnDefs = [{ field: 'make' }, { field: 'model' }, { field: 'price' }];

  rowData = [
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Porsche', model: 'Boxter', price: 72000 },
  ];


  modules = AllCommunityModules;
}
