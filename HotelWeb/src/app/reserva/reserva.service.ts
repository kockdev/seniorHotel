import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Reserva {
  id?: number;
  hospedeId: number;
  dtReservaInicio: string;
  dtReservaFim: string;
  dataCheckin?: string;
  dataCheckout?: string;
  carro: boolean;
  valorTotal?: number;
}

@Injectable({
  providedIn: 'root'
})
export class ReservaService {
  private apiUrl = 'http://localhost:8080/api/reservas';

  constructor(private http: HttpClient) {}

  // Método para listar todas as reservas
  getReservas(): Observable<Reserva[]> {
    return this.http.get<Reserva[]>(this.apiUrl);
  }

  // Método para criar uma nova reserva
  createReserva(reserva: Reserva): Observable<Reserva> {
    reserva.dtReservaInicio = `${reserva.dtReservaInicio}T00:00:00`;
    reserva.dtReservaFim = `${reserva.dtReservaFim}T00:00:00`;
    console.log(reserva)
    return this.http.post<Reserva>(this.apiUrl, reserva);
  }

  // Checkin da reserva
  checkinReserva(id: number): Observable<Reserva> {
    return this.http.post<Reserva>(`${this.apiUrl}/${id}/checkin`, {});
  }

  // Checkout da reserva e cálculo do valor da estadia
  checkoutReserva(id: number): Observable<Reserva> {
    return this.http.post<Reserva>(`${this.apiUrl}/${id}/checkout`, {});
  }
}
