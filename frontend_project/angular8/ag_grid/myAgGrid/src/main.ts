import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

import '@ag-grid-community/angular';
import '@ag-grid-community/all-modules/dist/styles/ag-grid.css';
import '@ag-grid-community/all-modules/dist/styles/ag-theme-balham.css';



if (environment.production) {
  enableProdMode();
}

// platformBrowserDynamic().bootstrapModule(AppModule)
//   .catch(err => console.error(err));


platformBrowserDynamic().bootstrapModule(AppModule).then(ref => {
    // Ensure Angular destroys itself on hot reloads.
    // Otherwise, log the boot error
  }).catch(err => console.error(err));
