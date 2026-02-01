package org.vladimirklgn.iikoreservationbot.service;

import org.vladimirklgn.iikoreservationbot.model.*;

import java.util.List;

public interface IikoApi {

    // Получить список доступных организаций (твои бары)

    List<Organization> getOrganizations() throws Exception;

    // Получить доступные временные слоты для брони в организации

    List<TimeSlot> getAvailableTimeSlots(String organizationId, String date, int guests) throws Exception;

    // Создать бронь
    Reservation createReservation(ReservationRequest request) throws Exception;

    // Другие методы по мере необходимости
}