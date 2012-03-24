/*
 *  ____    ____    _____    ____    ___     ____ 
 * |  _ \  |  _ \  | ____|  / ___|  / _/    / ___|        Precog (R)
 * | |_) | | |_) | |  _|   | |     | |  /| | |  _         Advanced Analytics Engine for NoSQL Data
 * |  __/  |  _ <  | |___  | |___  |/ _| | | |_| |        Copyright (C) 2010 - 2013 SlamData, Inc.
 * |_|     |_| \_\ |_____|  \____|   /__/   \____|        All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the 
 * GNU Affero General Public License as published by the Free Software Foundation, either version 
 * 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See 
 * the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this 
 * program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.precog
package daze

import yggdrasil._

import akka.dispatch.Await
import scalaz.Validation
import scalaz.effect.IO
import scalaz.iteratee._
import scalaz.syntax.monad._
import scalaz.std.set._
import Validation._
import Iteratee._

trait DatasetConsumersConfig extends EvaluatorConfig {
  def maxEvalDuration: akka.util.Duration
}

// TODO decouple this from the evaluator specifics
trait MemoryDatasetConsumer extends Evaluator with YggConfigComponent {
  type X = Throwable
  type Dataset[E] = DatasetEnum[X, E, IO]
  type YggConfig <: DatasetConsumersConfig 

  def error(msg: String, ex: Throwable): X = new RuntimeException(msg, ex)

  def consumeEval(userUID: String, graph: DepGraph): Validation[X, Set[SEvent]] = {
    sys.error("tofix")
    /*
    implicit val bind = Validation.validationMonad[Throwable]
    val validated: Validation[X, Validation[X, Set[SEvent]]] = Validation.fromTryCatch {
      Await.result(
        eval(userUID, graph).fenum.map { (enum: EnumeratorP[X, Vector[SEvent], IO]) => 
          (consume[X, Vector[SEvent], IO, Set] &= enum[IO]) map { s => success[X, Set[SEvent]](s.flatten) } run { err => IO(failure(err)) } unsafePerformIO
        },
        yggConfig.maxEvalDuration
      )
    } 
    
    validated.fail.map(err => error("Timed out after " + yggConfig.maxEvalDuration + " in consumeEval", err)).validation.join
    */
  }
}


// vim: set ts=4 sw=4 et: