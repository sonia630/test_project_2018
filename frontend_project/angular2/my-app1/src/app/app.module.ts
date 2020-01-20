import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';

import { HightLightDirective } from './hightlight.directive';

import { LoggerService } from './logger.service';
import { ChildComponent } from './child.component';

import * as _ from 'lodash';
import { JasmineComponentComponent } from './jasmine-component/jasmine-component.component';

@NgModule({
  imports: [AppRoutingModule, BrowserModule /*启动应用关键模块*/, FormsModule],
  declarations: [AppComponent, HightLightDirective, ChildComponent, JasmineComponentComponent], // 在这里填加指令
  exports: [],
  providers: [LoggerService], // service 注入
  bootstrap: [AppComponent],
})
export class AppModule {}

// import { BrowserModule } from '@angular/platform-browser';
// import { NgModule } from '@angular/core';

// import { AppRoutingModule } from './app-routing.module';
// import { AppComponent } from './app.component';
// import { ServiceWorkerModule } from '@angular/service-worker';
// import { environment } from '../environments/environment';

// @NgModule({
//   declarations: [
//     AppComponent
//   ],
//   imports: [
//     BrowserModule,
//     AppRoutingModule,
//     ServiceWorkerModule.register('ngsw-worker.js', { enabled: environment.production })
//   ],
//   providers: [],
//   bootstrap: [AppComponent]
// })
// export class AppModule { }
