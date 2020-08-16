/*
 * Copyright 2015-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.rsocket.kotlin.internal

import io.rsocket.kotlin.*
import io.rsocket.kotlin.flow.*
import io.rsocket.kotlin.frame.*
import io.rsocket.kotlin.payload.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

interface RSocketState : Cancelable {
    val streamIds: Map<Int, *>

    fun send(frame: Frame)
    fun sendPrioritized(frame: Frame)

    fun receiver(streamId: Int): ReceiveChannel<RequestFrame>

    suspend fun receiveOne(streamId: Int, receiver: ReceiveChannel<RequestFrame>): Payload
    suspend fun RequestingFlowCollector<Payload>.emitAll(streamId: Int, receiver: ReceiveChannel<RequestFrame>)

    fun RequestingFlow<Payload>.sendLimiting(streamId: Int, initialRequest: Int): ReceiveChannel<Payload>
    suspend fun CoroutineScope.sendStream(streamId: Int, stream: ReceiveChannel<Payload>)

    fun launch(block: suspend CoroutineScope.() -> Unit): Job
    fun launchCancelable(streamId: Int, block: suspend CoroutineScope.() -> Unit): Job

    fun requestingFlow(block: suspend RequestingFlowCollector<Payload>.() -> Unit): RequestingFlow<Payload>

    fun start(requestHandler: RSocket): Job
}
