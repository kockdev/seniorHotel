import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HospedeComponent } from '../../hospede/hospede.component';
import { ReservaComponent } from '../../reserva/reserva.component';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AdminComponent,
    HospedeComponent,
    ReservaComponent
    
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule,
    NzLayoutModule,
    NzIconModule,
    NzTableModule,
    NzButtonModule,
    NzFormModule,
    NzInputModule,
    NzModalModule,
    MatButtonModule,
    MatIconModule,
    NzSelectModule,
    FormsModule
  ]
})
export class AdminModule { }
