import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReservaService, Reserva } from './reserva.service';
import { NzModalService } from 'ng-zorro-antd/modal';
import { Hospede, HospedeService } from '../hospede/hospede.service';

@Component({
  selector: 'app-reserva',
  templateUrl: './reserva.component.html',
  styleUrls: ['./reserva.component.scss']
})
export class ReservaComponent implements OnInit {
  reservas: Reserva[] = [];
  reservaForm!: FormGroup;
  isVisible = false;
  hospedes: Hospede[] = [];

  constructor(
    private reservaService: ReservaService,
    private fb: FormBuilder,
    private modal: NzModalService,
    private hospedeService: HospedeService 
  ) {}

  ngOnInit(): void {
    this.loadReservas();
    this.loadHospedes();
    this.reservaForm = this.fb.group({
      hospedeId: ['', Validators.required],
      dtReservaInicio: ['', Validators.required],
      dtReservaFim: ['', Validators.required],
      carro: [false]
    });
  }

  // Carregar as reservas
  loadReservas(): void {
    this.reservaService.getReservas().subscribe(data => {
      this.reservas = data;
    });
  }

    // Carrega a lista de hóspedes
    loadHospedes(): void {
      this.hospedeService.getHospedes().subscribe(data => {
        this.hospedes = data;
      });
    }

  // Abrir modal para criar reserva
  openModal(): void {
    this.reservaForm.reset();
    this.isVisible = true;
  }

  // Fechar modal
  closeModal(): void {
    this.isVisible = false;
    this.reservaForm.reset();
  }

  // Criar uma nova reserva
  submitForm(): void {
    if (this.reservaForm.valid) {
      const reserva: Reserva = this.reservaForm.value;
      this.reservaService.createReserva(reserva).subscribe(() => {
        this.loadReservas();
        this.closeModal();
      });
    }
  }

  // Realizar Check-in
  checkinReserva(id: number): void {
    this.reservaService.checkinReserva(id).subscribe(() => {
      this.loadReservas();
    });
  }

  // Realizar Check-out e mostrar o valor total da estadia
  checkoutReserva(id: number): void {
    this.reservaService.checkoutReserva(id).subscribe(reserva => {
      this.loadReservas();
      const valorTotal = reserva.valorTotal !== undefined ? reserva.valorTotal.toFixed(2) : 'N/A';
      
      this.modal.info({
        nzTitle: 'Checkout realizado',
        nzContent: `Valor total da estadia: R$${valorTotal}`
      });
    });
  }
  
}
