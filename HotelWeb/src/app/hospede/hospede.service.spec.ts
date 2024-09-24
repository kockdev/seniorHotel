import { TestBed } from '@angular/core/testing';
import { HospedeService } from './hospede.service';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

describe('HospedeService', () => {
  let service: HospedeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        HospedeService,
        provideHttpClient(),
        provideHttpClientTesting() 
      ]
    });
    service = TestBed.inject(HospedeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
