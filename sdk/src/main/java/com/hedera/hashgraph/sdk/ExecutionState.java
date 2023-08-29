/*-
 *
 * Hedera Java SDK
 *
 * Copyright (C) 2020 - 2023 Hedera Hashgraph, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.hedera.hashgraph.sdk;

/**
 * Enum for the execution states.
 */
public enum ExecutionState {
    /**
     * Indicates that the execution was successful
     */
    SUCCESS,
    /**
     * Indicates that the call was successful but the operation did not complete. Retry with same/new node
     */
    RETRY,
    /**
     * Indicates that the receiver was bad node. Retry with new node
     */
    SERVER_ERROR,
    /**
     * Indicates that the request was incorrect
     */
    REQUEST_ERROR
}
