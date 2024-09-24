import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HospedeService, Hospede } from './hospede.service';
import { NzModalService } from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-hospede',
  templateUrl: './hospede.component.html',
  styleUrls: ['./hospede.component.scss']
})
export class HospedeComponent implements OnInit {
  hospedes: Hospede[] = [];
  hospedeForm!: FormGroup;
  isEdit = false;
  isVisible = false;
  currentHospedeId: number | null = null;
  searchTerm: string = '';
  searchFilter: string = 'nome';

  constructor(
    private hospedeService: HospedeService,
    private fb: FormBuilder,
    private modal: NzModalService
  ) {}

  ngOnInit(): void {
    this.loadHospedes();
    this.hospedeForm = this.fb.group({
      nome: ['', Validators.required],
      documento: ['', Validators.required],
      telefone: ['', Validators.required],
    });
  }


  loadHospedes(): void {
    this.hospedeService.getHospedes().subscribe(data => {
      this.hospedes = data;
    });
  }

  openModal(): void {
    this.hospedeForm.reset(); 
    this.isVisible = true; 
  }
  

  closeModal(): void {
    this.isVisible = false;
    this.hospedeForm.reset();
  }

  submitForm(): void {
    if (this.hospedeForm.valid) {
      const hospede: Hospede = this.hospedeForm.value;
      this.hospedeService.createHospede(hospede).subscribe(() => {
        this.loadHospedes();
        this.hospedeForm.reset();
      });
    }
  }

  onSearch(): void {
    if (this.searchFilter === 'nome') {
      this.hospedeService.buscarPorNome(this.searchTerm).subscribe(data => {
        this.hospedes = data;
      });
    } else if (this.searchFilter === 'documento') {
      this.hospedeService.buscarPorDocumento(this.searchTerm).subscribe(data => {
        this.hospedes = data;
      });
    } else if (this.searchFilter === 'telefone') {
      this.hospedeService.buscarPorTelefone(this.searchTerm).subscribe(data => {
        this.hospedes = data;
      });
    } else if (this.searchFilter === 'no-hotel') {
      this.hospedeService.listarHospedesNoHotel().subscribe(data => {
        this.hospedes = data;
      });
    } else if (this.searchFilter === 'sem-checkin') {
      this.hospedeService.listarHospedesSemCheckin().subscribe(data => {
        this.hospedes = data;
      });
    }
  }
}
