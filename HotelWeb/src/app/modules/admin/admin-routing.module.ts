import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { HospedeComponent } from '../../hospede/hospede.component';
import { ReservaComponent } from '../../reserva/reserva.component';

const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'hospede', component: HospedeComponent },
  { path: 'reserva', component: ReservaComponent },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
